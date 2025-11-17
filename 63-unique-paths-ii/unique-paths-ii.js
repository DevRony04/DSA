var uniquePathsWithObstacles = function(obstacleGrid) {
    const m = obstacleGrid.length;
    const n = obstacleGrid[0].length;

    if (obstacleGrid[0][0] === 1 || obstacleGrid[m-1][n-1] === 1) {
        return 0;
    }

    obstacleGrid[0][0] = 1;

    for (let j = 1; j < n; j++) {
        obstacleGrid[0][j] =
            (obstacleGrid[0][j] === 0 && obstacleGrid[0][j-1] === 1) ? 1 : 0;
    }

    for (let i = 1; i < m; i++) {
        obstacleGrid[i][0] =
            (obstacleGrid[i][0] === 0 && obstacleGrid[i-1][0] === 1) ? 1 : 0;
    }

    for (let i = 1; i < m; i++) {
        for (let j = 1; j < n; j++) {

            if (obstacleGrid[i][j] === 1) {
                obstacleGrid[i][j] = 0;
            } else {
                obstacleGrid[i][j] =
                    obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
            }

        }
    }

    return obstacleGrid[m-1][n-1];
};