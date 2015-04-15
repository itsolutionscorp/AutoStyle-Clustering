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
