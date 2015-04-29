class ETL

  def self.transform old_scores
    new_scores = {}
    old_scores.each do |key,value|
      value.map(&:downcase).each { |elem| new_scores[elem] = key }
    end
    new_scores
  end

end
