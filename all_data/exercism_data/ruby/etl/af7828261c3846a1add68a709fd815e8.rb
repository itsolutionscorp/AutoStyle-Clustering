class ETL
  def self.transform(old_way)
    new_way = {}
    old_way.each do |points, letter_arr|
      letter_arr.each do |letter|
        new_way[letter.downcase] = points
      end
    end

    new_way
  end
end
