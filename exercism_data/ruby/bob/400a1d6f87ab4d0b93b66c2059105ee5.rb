class Bob
  def initialize
    
  end

  def hey(comment)    
    return "Fine. Be that way!" if comment.strip == ""
    return 'Woah, chill out!' if comment == comment.upcase
    return 'Sure.' if comment.gsub("\n", "") =~ /\?$/
    "Whatever."
  end
end
