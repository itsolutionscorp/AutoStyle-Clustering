class Complement

  DNA = {"C" => "G",
         "G" => "C",
         "T" => "A",
         "A" => "U"}

  RNA = {"C" => "G",
         "G" => "C",
         "U" => "A",
         "A" => "T"}

  def self.method_missing(method, *args, &block)
    name = method.to_s
    super unless name.match(/of_[dr]na/)
    send(:match, name.slice(-3..-1).upcase, *args)
  end

  def self.match(type, letters)
    type = Complement.const_get(type)
    complements = letters.chars.map do |letter|
      type[letter]
    end
    complements.join
  end
end
