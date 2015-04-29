class ETL

  def self.transform(old_score)
    new_score = Hash.new()

    old_score.keys.map do |score|
      old_score[score].map {|letter| new_score[letter.downcase] = score}
    end

    new_score
  end

end
