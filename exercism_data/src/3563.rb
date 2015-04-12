class Hamming
  def compute(first_set, second_set)
    second_set = second_set.split('')
    result = 0

    first_set.split('').each_with_index do |letter, index|
      unless letter == second_set[index]
        result += 1
      end
    end

    result
  end
end
