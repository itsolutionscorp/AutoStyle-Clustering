class ETL

  def self.transform(scores)
    #Note to self
    #Hash = dictionary-like in python
    newScores = Hash.new()
    scores.each do |p,l|
      l.each do |letter|
        newScores[letter.downcase] = p
      end    
    end
    return newScores
  end

end
