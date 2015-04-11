class ETL

  def self.transform(system)
    new_system_hash = {}

    system.each do |points, letter_array|
      letter_array.each do |letters|
        new_system_hash[letters.downcase] = points
      end
    end
    new_system_hash
  end

end
