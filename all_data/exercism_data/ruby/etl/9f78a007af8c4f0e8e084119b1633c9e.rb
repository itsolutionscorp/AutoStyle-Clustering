class ETL

  def ETL.transform(old)
    new_point_map = old.inject({}) do |h, (k, v)| 
      v.each do |letter|
        # building new hash with each letter as key
        h[letter.downcase] = k
      end 
      h
    end
  end

end
