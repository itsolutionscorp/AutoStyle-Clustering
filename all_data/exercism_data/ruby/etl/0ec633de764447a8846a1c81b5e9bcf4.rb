class ETL

  def self.transform(old_fmt)  
    old_fmt.invert.each_with_object({}) do |(key_array, val), memo|
      memo.merge! build_new_fmt_subset(key_array, val)
    end
  end

  def self.build_new_fmt_subset(key_array, val)
    key_array.each_with_object({}) {|key,hsh| hsh[key.downcase] = val }
  end
end
