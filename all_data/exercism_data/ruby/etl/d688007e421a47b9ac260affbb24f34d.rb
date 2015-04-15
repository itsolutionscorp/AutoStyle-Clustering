class ETL

  def self.transform( orig_hash )
    trans_hash = orig_hash.inject({}) do |result, (score, letters)|
      letters.map! { |letter| letter.downcase }
      product_hash = Hash[ letters.product( [score] ) ]
      result.merge!( product_hash )
      result
    end
    
    trans_hash
  end

end
