class Atbash
  def self.encode(s)
    alpha = ('a'..'z').to_a.join
    rev = alpha.reverse
    s.downcase.gsub(/\W/, "").tr(alpha, rev).scan(/\w{1,5}/).join(" ")
  end
end
