INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) VALUES ('Tomato Garden', 6.0, 5.5, 2, 'Choose a sunny spot and use stakes for support.');

INSERT INTO material (project_id, material_name, num_required) VALUES (1, 'Tomato plants', 4);
INSERT INTO material (project_id, material_name, num_required) VALUES (1, 'Garden soil', 2);
INSERT INTO material (project_id, material_name, num_required) VALUES (1, 'Compost', 1);
INSERT INTO material (project_id, material_name, num_required) VALUES (1, 'Tomato stakes', 4);
INSERT INTO material (project_id, material_name, num_required) VALUES (1, 'Gardening gloves', 1);
INSERT INTO material (project_id, material_name, num_required) VALUES (1, 'Small shovel', 1);
INSERT INTO material (project_id, material_name, num_required) VALUES (1, 'Watering can', 1);

INSERT INTO step (project_id, step_text, step_order) VALUES (1, 'Prepare the soil by mixing garden soil and compost.', 1);
INSERT INTO step (project_id, step_text, step_order) VALUES (1, 'Dig holes for each tomato plant, spacing them about 2-3 feet apart.', 2);
INSERT INTO step (project_id, step_text, step_order) VALUES (1, 'Carefully plant the tomato plants, burying a portion of the stem for stronger roots.', 3);
INSERT INTO step (project_id, step_text, step_order) VALUES (1, 'Insert a tomato stake near each plant for support as they grow.', 4);
INSERT INTO step (project_id, step_text, step_order) VALUES (1, 'Water the plants thoroughly after planting.', 5);

INSERT INTO category (category_id, category_name) VALUES (1, 'Gardening');

INSERT INTO project_category (project_id, category_id) VALUES (1, 1);