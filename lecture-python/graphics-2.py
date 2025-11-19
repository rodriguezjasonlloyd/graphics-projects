import matplotlib.pyplot as plot
import numpy

# -

radius = 4
num_points = 200
translation = [12, 3, 7]

phi = numpy.linspace(0, numpy.pi, num_points // 2)
theta = numpy.linspace(0, 2 * numpy.pi, num_points)
phi, theta = numpy.meshgrid(phi, theta)

x = radius * numpy.sin(phi) * numpy.cos(theta)
y = radius * numpy.sin(phi) * numpy.sin(theta)
z = radius * numpy.cos(phi)

# -

x_translated = x + translation[0]
y_translated = y + translation[1]
z_translated = z + translation[2]

figure = plot.figure(figsize=(10, 5))

axes1 = figure.add_subplot(121, projection="3d")
axes1.plot_surface(x, y, z, color="blue", alpha=0.7)
axes1.set_title("Original Sphere")
axes1.set_xlim([-10, 15])
axes1.set_ylim([-10, 8])
axes1.set_zlim([-10, 10])

axes2 = figure.add_subplot(122, projection="3d")
axes2.plot_surface(x_translated, y_translated, z_translated, color="red", alpha=0.7)
axes2.set_title("Translated Sphere_")
axes2.set_xlim([-10, 15])
axes2.set_ylim([-10, 8])
axes2.set_zlim([-10, 10])

plot.show()

# -

radius = 5
height = 10
num_points = 200
scaling_factors = [2, 0.25, 3]

theta = numpy.linspace(0, 2 * numpy.pi, num_points)
z = numpy.linspace(0, height, num_points // 2)
theta, z = numpy.meshgrid(theta, z)

x = radius * numpy.cos(theta)
y = radius * numpy.sin(theta)

# -

x_scaled = x * scaling_factors[0]
y_scaled = y * scaling_factors[1]
z_scaled = z * scaling_factors[2]

figure = plot.figure(figsize=(10, 5))

axes1 = figure.add_subplot(121, projection="3d")
axes1.plot_surface(x, y, z, color="blue", alpha=0.7)
axes1.set_title("Original Cylinder")
axes1.set_xlim([-20, 20])
axes1.set_ylim([-20, 20])
axes1.set_zlim([0, 30])

axes2 = figure.add_subplot(122, projection="3d")
axes2.plot_surface(x_scaled, y_scaled, z_scaled, color="red", alpha=0.7)
axes2.set_title("Scaled Cylinder")
axes2.set_xlim([-20, 20])
axes2.set_ylim([-20, 20])
axes2.set_zlim([0, 30])

plot.show()

# -

radius = 3
height = 6
num_points = 200
rotation_angle = numpy.pi / 4

z = numpy.linspace(0, height, num_points // 2)
theta = numpy.linspace(0, 2 * numpy.pi, num_points)
z, theta = numpy.meshgrid(z, theta)

r = (height - z) / height * radius
x = r * numpy.cos(theta)
y = r * numpy.sin(theta)

# -

rotation_matrix = numpy.array(
    [
        [numpy.cos(rotation_angle), 0, numpy.sin(rotation_angle)],
        [0, 1, 0],
        [-numpy.sin(rotation_angle), 0, numpy.cos(rotation_angle)],
    ]
)

points = numpy.array([x.flatten(), y.flatten(), z.flatten()])
rotated_points = numpy.dot(rotation_matrix, points)

x_rotated = rotated_points[0].reshape(x.shape)
y_rotated = rotated_points[1].reshape(y.shape)
z_rotated = rotated_points[2].reshape(z.shape)

figure = plot.figure(figsize=(10, 5))

axes1 = figure.add_subplot(121, projection="3d")
axes1.plot_surface(x, y, z, color="blue", alpha=0.7)
axes1.set_title("Original Cone")
axes1.set_xlim([-5, 5])
axes1.set_ylim([-5, 5])
axes1.set_zlim([0, 10])

axes2 = figure.add_subplot(122, projection="3d")
axes2.plot_surface(x_rotated, y_rotated, z_rotated, color="red", alpha=0.7)
axes2.set_title("Rotated Cone (Y-Axis)")
axes2.set_xlim([-5, 5])
axes2.set_ylim([-5, 5])
axes2.set_zlim([0, 10])

plot.show()

# -

rotation_matrix = numpy.array(
    [
        [1, 0, 0],
        [0, numpy.cos(rotation_angle), -numpy.sin(rotation_angle)],
        [0, numpy.sin(rotation_angle), numpy.cos(rotation_angle)],
    ]
)

points = numpy.array([x.flatten(), y.flatten(), z.flatten()])
rotated_points = numpy.dot(rotation_matrix, points)

x_rotated = rotated_points[0].reshape(x.shape)
y_rotated = rotated_points[1].reshape(y.shape)
z_rotated = rotated_points[2].reshape(z.shape)

figure = plot.figure(figsize=(10, 5))

axes1 = figure.add_subplot(121, projection="3d")
axes1.plot_surface(x, y, z, color="blue", alpha=0.7)
axes1.set_title("Original Cone")
axes1.set_xlim([-5, 5])
axes1.set_ylim([-5, 5])
axes1.set_zlim([0, 10])

axes2 = figure.add_subplot(122, projection="3d")
axes2.plot_surface(x_rotated, y_rotated, z_rotated, color="red", alpha=0.7)
axes2.set_title("Rotated Cone (X-Axis)")
axes2.set_xlim([-5, 5])
axes2.set_ylim([-5, 5])
axes2.set_zlim([-5, 10])

plot.show()

# -

rotation_matrix = numpy.array(
    [
        [numpy.cos(rotation_angle), -numpy.sin(rotation_angle), 0],
        [numpy.sin(rotation_angle), numpy.cos(rotation_angle), 0],
        [0, 0, 1],
    ]
)

points = numpy.array([x.flatten(), y.flatten(), z.flatten()])
rotated_points = numpy.dot(rotation_matrix, points)

x_rotated = rotated_points[0].reshape(x.shape)
y_rotated = rotated_points[1].reshape(y.shape)
z_rotated = rotated_points[2].reshape(z.shape)

figure = plot.figure(figsize=(10, 5))

axes1 = figure.add_subplot(121, projection="3d")
axes1.plot_surface(x, y, z, color="blue", alpha=0.7)
axes1.set_title("Original Cone")
axes1.set_xlim([-5, 5])
axes1.set_ylim([-5, 5])
axes1.set_zlim([0, 10])

axes2 = figure.add_subplot(122, projection="3d")
axes2.plot_surface(x_rotated, y_rotated, z_rotated, color="red", alpha=0.7)
axes2.set_title("Rotated Cone (Z-Axis)")
axes2.set_xlim([-5, 5])
axes2.set_ylim([-5, 5])
axes2.set_zlim([0, 10])

plot.show()
