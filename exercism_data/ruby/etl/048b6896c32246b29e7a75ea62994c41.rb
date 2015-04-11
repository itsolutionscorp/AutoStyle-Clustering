class ETL

  def self.transform( orig_hash )
    trans_hash = orig_hash.inject({}) do |result, (score, letters)|
      letters.each { |letter|  result[letter.downcase] = score }
      result
    end
    
    trans_hash
  end

end
