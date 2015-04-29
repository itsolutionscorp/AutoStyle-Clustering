# I feel certain there must be a more efficient algorithm.
# But this seems alright for a first iteration.

def saddle_points(grid):
    def is_saddle_point(i, j):
        return (grid[i][j] >= max(grid[i]) and
                grid[i][j] <= min(grid[k][j] for k in range(len(grid))))

    if not all(len(grid[i]) == len(grid[0]) for i in range(1, len(grid))):
        raise ValueError('Provided matrix must be regular.')

    return {(i, j)
            for i in range(len(grid))
            for j in range(len(grid[i]))
            if is_saddle_point(i, j)}
