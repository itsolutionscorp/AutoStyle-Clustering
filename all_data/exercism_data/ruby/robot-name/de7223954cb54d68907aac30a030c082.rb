class Robot
  attr_accessor :identifier

  def name
    @identifier ||= generate_name(2,3)    
  end

  def reset 
    @identifier = nil
  end 

  private 

  def generate_name(total_letters, total_numbers)
    (letters(total_letters) + numbers(total_numbers)).join('')
  end

  def numbers(total)
    generate_random((1..9).to_a, total)
  end

  def letters(total)
    chars = ('a'..'z').to_a + ('A'..'Z').to_a
    generate_random(chars, total)
  end

  def generate_random(char_array, total_chars)
    chars = char_array.to_a
    total_chars.times.map{chars.sample}
  end
  
end
