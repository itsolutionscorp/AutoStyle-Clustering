require 'pry'

class Hamming 
  def compute(let1, let2)
    let1.chars.zip(let2.chars).inject(0) do |sum, element|
      element[0] != element[1] ? sum + 1 : sum + 0
    end
  end
end
