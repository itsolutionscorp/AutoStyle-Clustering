class Hamming
  def self.compute(string_a, string_b)
    array_a = string_to_array(string_a)
    array_b = string_to_array(string_b)
    compute_difference(array_a, array_b)
  end

  def self.compute_difference(array_a, array_b)
    diff_counter = 0
    array_a.each_with_index do |character, index|
      unless character == array_b[index]
        diff_counter = diff_counter + 1
      end
    end
    diff_counter
  end

  def self.string_to_array(string)
    string.split("")
  end
end
