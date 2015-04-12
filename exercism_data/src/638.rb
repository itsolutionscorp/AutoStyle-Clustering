class Phrase
  def initialize(str)
    @str = str
  end

  def word_count
    @str.gsub(/[^0-9A-Za-z ']/, ' ').split(/ /).inject(Hash.new(0)) { |e, v| e[v.downcase] += 1 unless v.strip == ""; e }
  end
end
