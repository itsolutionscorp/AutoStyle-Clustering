class Phrase
  def initialize(str)
    @text = str
  end

  def word_count
    if @count.nil? then
      @count = Hash.new 0
      @text.gsub(/[^[:alnum:]' ]/, " ").split(" ").map { |w| w.downcase }.each do |word|
        @count[word] += 1
      end
    end
    @count  
  end
end
