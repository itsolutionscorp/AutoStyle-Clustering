module Atbash
  def self.encode(input)
    @plain = ('a'..'z').to_a
    input.downcase.gsub(/\W/, "").chars
      .map { |c| c =~ /\d/ ? c : @plain.reverse[@plain.index(c)] }
      .each_slice(5).to_a.map { |g| g.join }.join(" ")
  end
end
