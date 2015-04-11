class Complement

  STRAND_HASH = {"C" => "G", "G" => "C", "A" => "T", "U" => "A"}

  def self.method_missing(method, *args, &block)
    if method.to_s =~ /^of_(.+)$/
      self.run_method($1, *args, &block)
    else
      super
    end
  end

  def self.run_method(attrs, *args, &block)

    strand_array = args[0].split(//)

    complement_array = []

    strand_array.each do |element|
      new_element = \
        attrs == 'dna' ? STRAND_HASH.invert[element] : STRAND_HASH[element]
      complement_array << new_element
    end

    complement_array.inject(:+)
  end

end
