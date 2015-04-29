class ETL
  def self.transform scores
    scores.inject({}) do |h,(point, letters)|
      letters.each do |letter|
        h[letter.downcase] = point
      end
      h
    end
  end
end
