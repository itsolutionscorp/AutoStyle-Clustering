class ETL
  def self.transform( old_data )
    new_data = {}

    old_data.each do | points, letters |
      letters.each do | single_letter |
        new_data[single_letter.downcase] = points
    end
    end

    new_data
  end
end
