-- CREATE TABLES
-- building
CREATE TABLE building (
	b_id VARCHAR(5) NOT NULL,
	name VARCHAR(50),
	pol POLYGON NOT NULL,
	fire INTEGER DEFAULT 0,

	PRIMARY KEY (b_id)
);

ALTER TABLE building ENGINE=myisam;

CREATE SPATIAL INDEX idx_building_pol ON building (pol);

-- hydrant
CREATE TABLE hydrant (
	h_id VARCHAR(5) NOT NULL,
	geo POINT NOT NULL,

	PRIMARY KEY (h_id)
);

ALTER TABLE hydrant ENGINE=myisam;

CREATE SPATIAL INDEX idx_hydrant_geo ON hydrant (geo);



-- INSERT
-- building
INSERT INTO building (b_id, name, pol, fire) VALUES ('b0', 'PSA', GeomFromText('POLYGON(( 79 68, 184 125, 179 133, 189 138, 139 229, 131 225, 127 233, 21 175, 26 168, 18 163, 67 73, 74 76, 79 68 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b1', 'OHE', GeomFromText('POLYGON(( 226 150, 254 164, 240 191, 212 176, 226 150 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b2', 'BHE', GeomFromText('POLYGON(( 337 209, 389 236, 385 242, 390 244, 385 253, 381 251, 378 255, 327 228, 330 223, 324 219, 328 212, 334 214, 337 209 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b3', 'VHE', GeomFromText('POLYGON(( 405 239, 440 257, 426 283, 421 280, 394 329, 401 332, 386 357, 351 339, 365 313, 369 315, 396 266, 391 263, 405 239 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b4', 'HED & PCE', GeomFromText('POLYGON(( 267 265, 291 278, 284 290, 290 294, 296 283, 354 314, 348 327, 340 322, 335 330, 290 305, 291 301, 281 295, 277 301, 258 289, 267 265 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b5', 'small building 1', GeomFromText('POLYGON(( 207 193, 219 199, 191 251, 179 245, 207 193 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b6', 'small building 2', GeomFromText('POLYGON(( 264 174, 273 179, 247 228, 237 222, 264 174 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b7', 'small building 3', GeomFromText('POLYGON(( 216 228, 241 241, 225 271, 199 257, 216 228 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b8', 'NBA', GeomFromText('POLYGON(( 182 284, 207 298, 202 306, 177 293, 182 284 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b9', 'ABT', GeomFromText('POLYGON(( 157 282, 169 288, 158 308, 147 301, 157 282 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b10', 'SBA', GeomFromText('POLYGON(( 172 304, 197 317, 191 327, 166 313, 172 304 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b11', 'GER', GeomFromText('POLYGON(( 65 226, 122 258, 77 341, 20 310, 65 226 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b12', 'EEB', GeomFromText('POLYGON(( 127 346, 184 375, 171 400, 115 370, 127 346 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b13', 'SAL', GeomFromText('POLYGON(( 216 370, 266 397, 255 417, 261 420, 247 445, 187 412, 201 388, 206 391, 216 370 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b14', 'SSC', GeomFromText('POLYGON(( 232 324, 241 329, 238 333, 245 338, 247 334, 293 358, 277 386, 233 360, 234 356, 228 352, 224 359, 217 354, 232 324 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b15', 'SSL', GeomFromText('POLYGON(( 309 371, 325 379, 333 365, 349 374, 342 388, 357 396, 346 416, 331 409, 324 422, 307 413, 313 399, 298 391, 309 371 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b16', 'PHE', GeomFromText('POLYGON(( 300 432, 338 452, 322 482, 296 469, 302 459, 289 452, 300 432 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b17', 'LHI & SLH', GeomFromText('POLYGON(( 446 339, 478 357, 460 390, 491 408, 488 417, 424 382, 446 339 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b18', 'HLAG', GeomFromText('POLYGON(( 420 426, 464 449, 435 503, 392 480, 420 426 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b19', 'RRB & LIS & OCW & CEM & SCI', GeomFromText('POLYGON(( 474 285, 529 315, 533 310, 695 397, 644 494, 601 471, 644 390, 627 380, 623 387, 633 393, 609 434, 586 422, 610 371, 524 325, 508 353, 454 323, 474 285 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b20', 'SHS', GeomFromText('POLYGON(( 565 357, 596 374, 582 399, 551 383, 565 357 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b21', 'ACP', GeomFromText('POLYGON(( 564 425, 585 436, 573 458, 552 447, 564 425 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b22', 'HAR', GeomFromText('POLYGON(( 498 459, 574 503, 537 569, 465 530, 458 541, 439 530, 445 517, 462 525, 498 459 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b23', 'MPH', GeomFromText('POLYGON(( 586 500, 629 523, 596 578, 569 563, 559 579, 549 574, 586 500 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b24', 'ACC', GeomFromText('POLYGON(( 748 427, 791 450, 768 494, 725 471, 748 427 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b25', 'BRI', GeomFromText('POLYGON(( 722 483, 735 490, 729 505, 757 518, 746 538, 719 525, 710 535, 698 528, 722 483 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b26', 'SGM', GeomFromText('POLYGON(( 297 13, 357 45, 334 87, 312 75, 291 114, 310 125, 295 151, 239 121, 253 95, 275 106, 280 98, 259 86, 270 62, 293 73, 297 66, 275 54, 297 13 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b27', 'GFS', GeomFromText('POLYGON(( 380 66, 493 126, 475 157, 363 97, 380 66 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b28', 'BKS', GeomFromText('POLYGON(( 607 199, 641 217, 617 257, 584 239, 607 199 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b29', 'HSH', GeomFromText('POLYGON(( 552 231, 565 238, 540 284, 527 278, 552 231 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b30', 'YWC', GeomFromText('POLYGON(( 514 215, 537 224, 530 237, 521 232, 513 248, 530 258, 527 264, 497 247, 514 215 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b31', 'commons & STU', GeomFromText('POLYGON(( 659 228, 700 249, 692 263, 708 272, 718 255, 766 280, 740 329, 646 278, 640 290, 619 278, 640 236, 652 241, 659 228 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b32', 'TSC', GeomFromText('POLYGON(( 677 320, 708 337, 690 368, 661 351, 677 320 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b33', 'HNB', GeomFromText('POLYGON(( 431 163, 440 160, 443 171, 472 186, 482 183, 485 195, 474 198, 458 227, 462 236, 452 239, 449 230, 421 214, 410 216, 408 207, 417 205, 434 175, 431 163 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b34', 'PKS', GeomFromText('POLYGON(( 94 402, 110 412, 105 420, 115 426, 118 422, 176 454, 165 472, 108 441, 110 435, 100 430, 74 480, 57 470, 94 402 ))'), 0);
INSERT INTO building (b_id, name, pol, fire) VALUES ('b35', 'STO', GeomFromText('POLYGON(( 574 247, 586 254, 577 270, 589 277, 599 259, 610 265, 587 309, 575 302, 581 290, 570 283, 562 296, 552 289, 574 247 ))'), 0);

UPDATE building SET fire = 1 WHERE name IN ('OHE', 'ACP', 'TSC');

-- hydrant
INSERT INTO hydrant (h_id, geo) VALUES ('p0', GeomFromText('POINT(228 102)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p1', GeomFromText('POINT(220 112)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p2', GeomFromText('POINT(213 135)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p3', GeomFromText('POINT(189 177)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p4', GeomFromText('POINT(180 202)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p5', GeomFromText('POINT(166 231)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p6', GeomFromText('POINT(153 257)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p7', GeomFromText('POINT(138 278)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p8', GeomFromText('POINT(125 300)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p9', GeomFromText('POINT(116 320)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p10', GeomFromText('POINT(94 369)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p11', GeomFromText('POINT(104 394)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p12', GeomFromText('POINT(136 409)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p13', GeomFromText('POINT(171 430)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p14', GeomFromText('POINT(195 438)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p15', GeomFromText('POINT(221 453)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p16', GeomFromText('POINT(250 462)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p17', GeomFromText('POINT(283 485)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p18', GeomFromText('POINT(320 505)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p19', GeomFromText('POINT(342 502)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p20', GeomFromText('POINT(365 475)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p21', GeomFromText('POINT(320 531)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p22', GeomFromText('POINT(318 561)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p23', GeomFromText('POINT(439 318)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p24', GeomFromText('POINT(422 344)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p25', GeomFromText('POINT(412 378)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p26', GeomFromText('POINT(484 233)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p27', GeomFromText('POINT(504 208)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p28', GeomFromText('POINT(518 175)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p29', GeomFromText('POINT(516 290)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p30', GeomFromText('POINT(531 299)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p31', GeomFromText('POINT(588 328)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p32', GeomFromText('POINT(637 352)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p33', GeomFromText('POINT(657 363)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p34', GeomFromText('POINT(448 409)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p35', GeomFromText('POINT(464 420)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p36', GeomFromText('POINT(484 428)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p37', GeomFromText('POINT(491 432)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p38', GeomFromText('POINT(507 438)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p39', GeomFromText('POINT(548 462)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p40', GeomFromText('POINT(583 480)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p41', GeomFromText('POINT(302 197)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p42', GeomFromText('POINT(290 188)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p43', GeomFromText('POINT(297 202)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p44', GeomFromText('POINT(358 199)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p45', GeomFromText('POINT(384 214)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p46', GeomFromText('POINT(281 162)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p47', GeomFromText('POINT(264 314)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p48', GeomFromText('POINT(256 302)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p49', GeomFromText('POINT(221 285)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p50', GeomFromText('POINT(197 275)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p51', GeomFromText('POINT(179 338)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p52', GeomFromText('POINT(161 325)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p53', GeomFromText('POINT(378 280)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p54', GeomFromText('POINT(362 267)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p55', GeomFromText('POINT(530 364)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p56', GeomFromText('POINT(535 353)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p57', GeomFromText('POINT(589 448)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p58', GeomFromText('POINT(691 456)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p59', GeomFromText('POINT(698 427)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p60', GeomFromText('POINT(716 430)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p61', GeomFromText('POINT(723 446)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p62', GeomFromText('POINT(687 503)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p63', GeomFromText('POINT(674 525)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p64', GeomFromText('POINT(652 523)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p65', GeomFromText('POINT(645 539)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p66', GeomFromText('POINT(659 309)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p67', GeomFromText('POINT(716 340)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p68', GeomFromText('POINT(524 150)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p69', GeomFromText('POINT(476 258)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p70', GeomFromText('POINT(241 269)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p71', GeomFromText('POINT(284 409)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p72', GeomFromText('POINT(272 409)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p73', GeomFromText('POINT(204 367)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p74', GeomFromText('POINT(231 371)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p75', GeomFromText('POINT(381 424)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p76', GeomFromText('POINT(466 471)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p77', GeomFromText('POINT(338 96)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p78', GeomFromText('POINT(349 78)'));
INSERT INTO hydrant (h_id, geo) VALUES ('p79', GeomFromText('POINT(409 132)'));


COMMIT;