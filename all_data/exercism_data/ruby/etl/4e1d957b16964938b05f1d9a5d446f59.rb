class ETL
  def self.transform(old_map)
    new_map = {}
    old_map.each do |points, letters|
      letters.each do |letter|
        new_map[letter.downcase] = points
      end
    end
    new_map
  end
end
