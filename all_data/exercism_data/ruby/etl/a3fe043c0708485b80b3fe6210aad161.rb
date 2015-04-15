class ETL

  def self.transform(old_fmt)  
    inverted = old_fmt.invert
    inverted.each_with_object({}) do |(key_array, val), new_fmt|
      new_fmt.merge! build_new_fmt_subset(key_array, val)
    end
  end

  def self.build_new_fmt_subset(key_array, val)
    key_array.each_with_object({}) do |key,hsh|
      hsh[key.downcase] = val
    end
  end
end
