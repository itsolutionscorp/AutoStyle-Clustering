class ETL
  def ETL.transform(old)
    old.each_with_object({}) do |(key,values), new|
      values.each do |key_new|
        new[key_new.downcase] = key
      end
    end
  end
end
