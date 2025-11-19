import math

import matplotlib.pyplot as plot
import numpy
from mpl_toolkits.mplot3d.art3d import Poly3DCollection

# -

radius = 6
height = 11
num_points = 400

theta = numpy.linspace(0, 2 * numpy.pi, num_points)
z = numpy.linspace(0, height, num_points)
theta, z = numpy.meshgrid(theta, z)

x = radius = numpy.cos(theta)
y = radius = numpy.sin(theta)

figure = plot.figure()
axes = figure.add_subplot(111, projection="3d")
axes.plot_surface(x, y, z, rstride=5, cstride=5, color="red", edgecolor="yellow")
axes.set_xlabel("X")
axes.set_ylabel("Y")
axes.set_zlabel("Z")
axes.set_title("Cylinder - Rodriguez")
plot.show()

# -


def volume_cylinder(radius, height):
    return math.pi * radius**2 * height


radius_cylinder = 6
height_cylinder = 11

print("volume of cylinder", volume_cylinder(radius_cylinder, height_cylinder))

# -

radius = 6
height = 11
num_points = 400

theta = numpy.linspace(0, 2 * numpy.pi, num_points)
z = numpy.linspace(0, height, num_points)
theta, z = numpy.meshgrid(theta, z)

x = radius * (1 - z / height) * numpy.cos(theta)
y = radius * (1 - z / height) * numpy.sin(theta)

figure = plot.figure()
axes = figure.add_subplot(111, projection="3d")
axes.plot_surface(x, y, z, rstride=5, cstride=5, color="red", edgecolor="blue")
axes.set_xlabel("X")
axes.set_ylabel("Y")
axes.set_zlabel("Z")
axes.set_title("Cone - Rodriguez")
plot.show()

# -


def volume_cone(radius, height):
    return (1 / 3) * math.pi * radius**2 * height


radius_cone = 6
height_cone = 11

print("volume of cone", volume_cone(radius_cone, height_cone))

# -

raidius = 6
num_points = 400

theta = numpy.linspace(0, 2 * numpy.pi, num_points)
phi = numpy.linspace(0, numpy.pi, num_points)
theta, phi = numpy.meshgrid(theta, phi)

x = radius * numpy.sin(phi) * numpy.cos(theta)
y = radius * numpy.sin(phi) * numpy.sin(theta)
z = radius * numpy.cos(phi)

figure = plot.figure()
axes = figure.add_subplot(111, projection="3d")
axes.plot_surface(x, y, z, rstride=5, cstride=5, color="red", edgecolor="green")
axes.set_xlabel("X")
axes.set_ylabel("Y")
axes.set_zlabel("Z")
axes.set_title("Sphere - Rodriguez")
plot.show()

# -


def volume_sphere(radius):
    return (4 / 3) * math.pi * radius**3


radius_sphere = 6

print("volume of sphere", volume_sphere(radius_sphere), "cubic units")

# -

vertices = numpy.array(
    [
        [0, 0, 0],
        [1, 0, 0],
        [1, 1, 0],
        [0, 1, 0],
        [0, 0, 1],
        [1, 0, 1],
        [1, 1, 1],
        [0, 1, 1],
    ]
)

faces = [
    [vertices[0], vertices[1], vertices[2], vertices[3]],
    [vertices[4], vertices[5], vertices[6], vertices[7]],
    [vertices[0], vertices[1], vertices[5], vertices[4]],
    [vertices[2], vertices[3], vertices[7], vertices[6]],
    [vertices[1], vertices[2], vertices[6], vertices[5]],
    [vertices[4], vertices[7], vertices[3], vertices[0]],
]

figure = plot.figure()
axes = figure.add_subplot(111, projection="3d")

axes.add_collection3d(
    Poly3DCollection(
        faces, facecolors="red", edgecolors="green", linewidths=1, alpha=0.3
    )
)

axes.set_xlim(0, 1)
axes.set_ylim(0, 1)
axes.set_zlim(0, 1)

axes.set_xlabel("X")
axes.set_ylabel("Y")
axes.set_zlabel("Z")
axes.set_title("Cube - Rodriguez")

plot.show()

# -

initial_corner = numpy.array([1, 1, 7])
edge_length = 3

vertices = [
    initial_corner,
    initial_corner + ([edge_length, 0, 0]),
    initial_corner + ([edge_length, edge_length, 0]),
    initial_corner + ([0, edge_length, 0]),
    initial_corner + ([0, 0, edge_length]),
    initial_corner + ([edge_length, 0, edge_length]),
    initial_corner + ([edge_length, edge_length, edge_length]),
    initial_corner + ([0, edge_length, edge_length]),
]

faces = [
    [vertices[0], vertices[1], vertices[2], vertices[3]],
    [vertices[4], vertices[5], vertices[6], vertices[7]],
    [vertices[0], vertices[1], vertices[5], vertices[4]],
    [vertices[2], vertices[3], vertices[7], vertices[6]],
    [vertices[1], vertices[2], vertices[6], vertices[5]],
    [vertices[4], vertices[7], vertices[3], vertices[0]],
]

figure = plot.figure()
axes = figure.add_subplot(111, projection="3d")

axes.add_collection3d(
    Poly3DCollection(
        faces, facecolors="red", linewidths=1, edgecolors="blue", alpha=0.3
    )
)

axes.set_xlabel("X")
axes.set_ylabel("Y")
axes.set_zlabel("Z")
axes.set_title("Cube - Rodriguez")

axes.set_xlim(initial_corner[0], initial_corner[0] + edge_length)
axes.set_ylim(initial_corner[1], initial_corner[1] + edge_length)
axes.set_zlim(initial_corner[2], initial_corner[2] + edge_length)

plot.show()

# -


def distance_3d(point1, point2):
    point1 = numpy.array(point1)
    point2 = numpy.array(point2)

    distance = numpy.sqrt(numpy.sum(point2 - point1) ** 2)

    return distance


point_a = (1, 2, 3)
point_b = (4, 5, 6)

distance = distance_3d(point_a, point_b)

print(f"the distance between {point_a} and {point_b} is: {distance}")

# -


def volume_cube(side_cube):
    return side_cube**3


side_cube = 3

print("volume of cube:", volume_cube(side_cube), "cubic units")

# -

base_size = 16
height = 9

vertices = numpy.array(
    [
        [-base_size / 2, -base_size / 2, 0],
        [base_size / 2, -base_size / 2, 0],
        [base_size / 2, base_size / 2, 0],
        [-base_size / 2, base_size / 2, 0],
        [0, 0, height],
    ]
)

faces = [
    [vertices[0], vertices[1], vertices[4]],
    [vertices[1], vertices[2], vertices[4]],
    [vertices[2], vertices[3], vertices[4]],
    [vertices[3], vertices[0], vertices[4]],
    [vertices[0], vertices[1], vertices[2], vertices[3]],
]

figure = plot.figure()
axes = figure.add_subplot(111, projection="3d")

axes.add_collection3d(
    Poly3DCollection(
        faces, facecolors="red", edgecolors="blue", linewidths=1, alpha=0.5
    )
)

axes.set_xlim(-base_size, base_size)
axes.set_ylim(-base_size, base_size)
axes.set_zlim(0, height)

axes.set_xlabel("X")
axes.set_ylabel("Y")
axes.set_zlabel("Z")
axes.set_title("Pyramid - Rodriguez")

plot.show()

# -


def volume_pyramid(base_area_pyramid, height_pyramid):
    return (1 / 3) * base_area_pyramid * height_pyramid


base_area_pyramid = 16
height_pyramid = 9

print(
    "volume of pyramid:",
    volume_pyramid(base_area_pyramid, height_pyramid),
    "cubic units",
)

# -

length = 2
width = 3
height = 10

vertices = numpy.array(
    [
        [0, 0, 0],
        [length, 0, 0],
        [length, width, 0],
        [0, width, 0],
        [0, 0, height],
        [length, 0, height],
        [length, width, height],
        [0, width, height],
    ]
)

faces = [
    [vertices[0], vertices[1], vertices[2], vertices[3]],
    [vertices[4], vertices[5], vertices[6], vertices[7]],
    [vertices[0], vertices[1], vertices[5], vertices[4]],
    [vertices[1], vertices[2], vertices[6], vertices[5]],
    [vertices[2], vertices[3], vertices[7], vertices[6]],
    [vertices[3], vertices[0], vertices[4], vertices[7]],
]

figure = plot.figure()
axes = figure.add_subplot(111, projection="3d")

axes.add_collection3d(
    Poly3DCollection(
        faces, facecolors="lime", edgecolors="green", linewidths=1, alpha=0.5
    )
)

axes.set_xlim(0, length)
axes.set_ylim(0, width)
axes.set_zlim(0, height)

axes.set_xlabel("X")
axes.set_ylabel("Y")
axes.set_zlabel("Z")
axes.set_title("RectangularPrism - Rodriguez")

plot.show()

# -


def volume_prism(length_prism, width_prism, height_prism):
    return length_prism * width_prism * height_prism


length_prism = 2
width_prism = 3
height_prism = 4

print(
    "volume of prism:",
    volume_prism(length_prism, width_prism, height_prism),
    "cubic units",
)

# -

grid_size = 5
voxel_size = 1

figure = plot.figure()
axes = figure.add_subplot(111, projection="3d")

for x in range(grid_size):
    for y in range(grid_size):
        for z in range(grid_size):
            x_pos = x * voxel_size
            y_pos = y * voxel_size
            z_pos = z * voxel_size

            axes.bar3d(
                x_pos,
                y_pos,
                z_pos,
                voxel_size,
                voxel_size,
                voxel_size,
                color="blue",
                linewidths=0.5,
            )

axes.set_xlabel("X")
axes.set_ylabel("Y")
axes.set_zlabel("Z")
axes.view_init(20, 75)

plot.show()
