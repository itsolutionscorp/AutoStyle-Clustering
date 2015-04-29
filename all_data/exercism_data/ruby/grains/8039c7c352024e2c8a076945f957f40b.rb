class Grains
  def square( board_space_number )
    board_space_number == 1 ? 1 : calculate_grains( board_space_number )
  end

  def total
    total_grains = 1

    63.times do | this_board_space |
      this_board_space += 2
      total_grains += calculate_grains( this_board_space )
    end

    total_grains
  end

  private
  def calculate_grains( board_space_number )
    2 ** (board_space_number - 1)
  end
end
