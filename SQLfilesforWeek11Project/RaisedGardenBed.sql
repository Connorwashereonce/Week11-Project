INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) VALUES ('Raised Garden Bed', 10.0, 8.5, 3, 'Use rot-resistant wood like cedar or redwood.');

INSERT INTO material (project_id, material_name, num_required) VALUES (2, '2x6x8 foot cedar boards', 6);
INSERT INTO material (project_id, material_name, num_required) VALUES (2, '4x4x1 foot cedar posts', 4);
INSERT INTO material (project_id, material_name, num_required) VALUES (2, '3 inch wood screws', 48);
INSERT INTO material (project_id, material_name, num_required) VALUES (2, 'Landscape fabric', 1);
INSERT INTO material (project_id, material_name, num_required) VALUES (2, 'Staple gun', 1);
INSERT INTO material (project_id, material_name, num_required) VALUES (2, 'Drill', 1);
INSERT INTO material (project_id, material_name, num_required) VALUES (2, 'Measuring tape', 1);
INSERT INTO material (project_id, material_name, num_required) VALUES (2, 'Saw', 1);

INSERT INTO step (project_id, step_text, step_order) VALUES (2, 'Cut two of the 2x6x8 foot boards in half to create four 4-foot long pieces.', 1);
INSERT INTO step (project_id, step_text, step_order) VALUES (2, 'Assemble the frame using the 8-foot boards for the long sides and the 4-foot boards for the short sides. Secure corners with wood screws.', 2);
INSERT INTO step (project_id, step_text, step_order) VALUES (2, 'Attach the 4x4x1 foot cedar posts to the inside corners of the assembled frame for added stability.', 3);
INSERT INTO step (project_id, step_text, step_order) VALUES (2, 'Line the inside of the raised bed with landscape fabric, securing it with a staple gun to prevent soil erosion.', 4);
INSERT INTO step (project_id, step_text, step_order) VALUES (2, 'Fill the raised bed with a mixture of good quality topsoil and compost.', 5);


INSERT INTO project_category (project_id, category_id) VALUES (2, 1);