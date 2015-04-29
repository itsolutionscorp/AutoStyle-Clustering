#old = { 1 => ['A'] }
#expected = { 'a' => 1 }

#old = { 1 => ['A', 'E', 'I', 'O', 'U'] }
#expected = { 'a' => 1, 'e' => 1, 'i' => 1, 'o' => 1, 'u' => 1 }



class ETL
  def self.transform(old_system)
    new_system = {}
    old_system.each do |points, letter_array|
      letter_array.each do |letter|
        new_system[letter.downcase] = points
      end
    end
    new_system
  end
end
