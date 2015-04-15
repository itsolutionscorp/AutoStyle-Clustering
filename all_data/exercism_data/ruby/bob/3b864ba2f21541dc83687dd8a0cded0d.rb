class Bob
  def initialize
    @matchers = {
      -> (s) { s.strip.empty? } => 'Fine. Be that way!',

      -> (s) {
        letters = s.gsub(/[^a-zA-Z]/, '')
        letters.length > 0 && letters.upcase == letters
      } => 'Woah, chill out!',

      -> (s) { s.strip.end_with? '?' } => 'Sure.'
    }
  end

  def hey(statement)
    match = @matchers.keys.detect { |matcher| matcher[statement] }
    @matchers[match] || 'Whatever.'
  end
end
