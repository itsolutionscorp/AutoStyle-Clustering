class ETL
  def self.transform(old_keys)
    new_values = {}

    old_keys.each do |key, value|
      if value.is_a? Array
        value.each do |v|
          new_values.merge! v.downcase => key
        end
      else
        new_values.merge! value.downcase => key
      end
    end
    new_values
  end

end
