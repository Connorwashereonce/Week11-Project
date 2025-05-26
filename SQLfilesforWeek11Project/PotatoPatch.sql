INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) VALUES ('Potato Patch', 8.0, 7.0, 3, 'Choose disease-free seed potatoes.');

INSERT INTO material (project_id, material_name, num_required) VALUES (3, 'Seed potatoes', 10);
INSERT INTO material (project_id, material_name, num_required) VALUES (3, 'Compost', 1);
INSERT INTO material (project_id, material_name, num_required) VALUES (3, 'Garden fork', 1);
INSERT INTO material (project_id, material_name, num_required) VALUES (3, 'Trowel', 1);
INSERT INTO material (project_id, material_name, num_required) VALUES (3, 'Watering hose', 1);
INSERT INTO material (project_id, material_name, num_required) VALUES (3, 'Gardening gloves', 1);

INSERT INTO step (project_id, step_text, step_order) VALUES (3, 'Prepare the planting area by loosening soil and adding compost.', 1);
INSERT INTO step (project_id, step_text, step_order) VALUES (3, 'Cut large seed potatoes into chunks with at least two "eyes" each.', 2);
INSERT INTO step (project_id, step_text, step_order) VALUES (3, 'Plant seed potato chunks 4-6 inches deep and 12 inches apart.', 3);
INSERT INTO step (project_id, step_text, step_order) VALUES (3, 'As plants grow, "hill" the soil around the stems to cover developing potatoes.', 4);
INSERT INTO step (project_id, step_text, step_order) VALUES (3, 'Water regularly, especially during flowering and tuber development.', 5);



INSERT INTO project_category (project_id, category_id) VALUES (3, 1);