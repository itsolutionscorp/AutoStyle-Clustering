class Atbash
  def self.encode(str)
    alphabet = ('a'..'z').to_a
    new_str = str.gsub(/\W/, "").downcase
    results = []
    new_str.tr(alphabet.to_s, alphabet.reverse.to_s).chars.each_slice(5)
      .map do |i|
      results << i.join
    end
    results.join(" ")
  end

end
