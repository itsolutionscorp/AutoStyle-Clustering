class ETL

  def self.transform(old_fmt)
    new_fmt = {}
    old_fmt.invert.each_pair do |key_array,val|
      new_fmt.merge! build_new_fmt_subset(key_array, val)
    end
    new_fmt
  end

  def self.build_new_fmt_subset(key_array, val)
    key_array.each_with_object({}) {|key,hsh| hsh[key.downcase] = val }
  end
end
