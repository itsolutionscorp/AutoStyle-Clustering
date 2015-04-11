class ETL
  def self.transform(v1_points)
    v1_points.each_with_object({}) do |(points, letters), v2_points|
      letters.each { |letter| v2_points[letter.downcase] = points }
    end
  end
end
