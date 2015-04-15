class Hamming
  def self.compute(first_var, second_var)
    first_var = first_var.split("")
    second_var = second_var.split("")
    diff_counter = 0
    first_var.each_with_index do |x, i|
      if x != second_var[i]
        diff_counter += 1
      end
    end
    return diff_counter
  end
end
