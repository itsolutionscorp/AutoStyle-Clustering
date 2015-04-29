#Looking to neaten this up.
class Complement

  @output = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
  }

  
  def self.of_dna(letter)
    result = ""
    letter.split("").each do |i|
      @output.each do |number, array|   
        if number == i       
          result += array
        end
      end
    end
    result 
  end
  
  def self.of_rna(letter)
    result = ""
    letter.split("").each do |i|
      @output.invert.each do |number, array|
        if number == i
          result += array
        end
      end
    end
    result 
  end

end  
