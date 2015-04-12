class Hamming

  def compute(string_one, string_two)
    array_of_letters_one = string_one.split(//)
    array_of_letters_two = string_two.split(//)

    if array_of_letters_one.length > array_of_letters_two.length
      array_of_letters_one = array_of_letters_one.slice(0..-2)
    elsif array_of_letters_two.length > array_of_letters_one.length
      array_of_letters_two = array_of_letters_two.slice(0..-2)
    end

    array_of_letters_one
    array_of_letters_two
    zipped_letters = array_of_letters_one.zip array_of_letters_two

    hamming_difference = 0

    zipped_letters.each do |pair|
      if pair[0] != pair[1]
        hamming_difference += 1
      end
    end
    hamming_difference
  end

end
