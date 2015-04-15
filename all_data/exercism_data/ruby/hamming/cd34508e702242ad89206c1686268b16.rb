class Hamming
  class << self
    def compute left, right
      total_distance = 0

      (0...left.length).each do |index|
        break if index >= right.length

        total_distance += calculate_distance(
          left[index],
          right[index]
        )
      end
      total_distance
    end

    private
    def calculate_distance left_character, right_character
      left_character == right_character ? 0 : 1
    end
  end
end
