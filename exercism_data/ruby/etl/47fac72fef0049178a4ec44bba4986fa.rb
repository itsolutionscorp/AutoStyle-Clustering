class ETL
  def self.transform(old)
    new_representation = {}
    old.each do |points, characters|
      characters.each do |char|
        new_representation[char.downcase] = points
      end
    end

    new_representation
  end
end
