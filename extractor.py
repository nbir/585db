#!/usr/bin/python
# -*- coding: utf-8 -*-



import os
import sys
import csv

def get_buildings():
	SQL = []
	cr = csv.reader(open('building.xy', 'rb'))
	for row in cr:
		row = [e.strip() for e in row]
		b_id = row.pop(0)
		name = row.pop(0)
		n = int(row.pop(0))
		pol = []
		for i in range(0, n):
			pol.append('{0} {1}'.format(row.pop(0), row.pop(0)))
		pol.append(pol[0])

		SQL.append("INSERT INTO building (b_id, name, pol, fire) VALUES ('{b_id}', '{name}', GeomFromText('POLYGON(( {pol} ))'), 0);".format(b_id=b_id, name=name, pol=', '.join(pol)))

	with open('INSERT_building.sql', 'wb') as fp:
		fp.write('\n'.join(SQL))

def get_hydrants():
	SQL = []
	cr = csv.reader(open('hydrant.xy', 'rb'))
	for row in cr:
		row = [e.strip() for e in row]
		h_id = row.pop(0)
		xy = '{0} {1}'.format(row.pop(0), row.pop(0))

		SQL.append("INSERT INTO hydrant (h_id, geo) VALUES ('{h_id}', GeomFromText('POINT({geo})'));".format(h_id=h_id, geo=xy))

	with open('INSERT_hydrant.sql', 'wb') as fp:
		fp.write('\n'.join(SQL))


if __name__ == "__main__":
	get_buildings()
	get_hydrants()
