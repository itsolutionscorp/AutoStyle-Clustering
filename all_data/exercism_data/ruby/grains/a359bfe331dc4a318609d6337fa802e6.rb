class Grains
	def square (value)
    grains = 1
    (value - 1).times { grains += grains }
    return grains
	end
  def total (board_size=64)
    running_total = 0
    board_size.times do |square_num|
      running_total += square(square_num + 1)
    end
    return running_total
  end
end
