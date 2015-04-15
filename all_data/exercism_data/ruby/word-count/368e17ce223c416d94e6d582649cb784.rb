class Phrase

  def initialize(text)
    @text = text
    avada_kedavra!
  end

  def avada_kedavra!
    @word_count = @text.split(/[^a-zA-Z0-9\']/).map{|x| x.downcase}.select{|x| x != "" }.reduce(Hash.new(0)) {|x,y| x[y] += 1; x }
  end

  def word_count
    @word_count
  end
end
