class Complement

  def self.of_dna(item)
    array = item.chars
    new_array = []
    array.each do |x|
      if x == 'C'
        new_array << 'G'
      elsif x == 'G'
        new_array << 'C'
      elsif x == 'T'
        new_array << 'A'
      else x == 'A'
        new_array << 'U'
      end
    end
    new_array.join
  end

  def self.of_rna(item)
    array = item.chars
    new_array = []
    array.each do |x|
      if x == 'C'
        new_array << 'G'
      elsif x == 'G'
        new_array << 'C'
      elsif x == 'U'
        new_array << 'A'
      else x == 'A'
        new_array << 'T'
      end
    end
    new_array.join
  end

end
