class Acronym

  def self.abbreviate(str)
    @h = ""
    @ary = Array.new
    @ary2 = Array.new
    str.gsub('-',' ').scan(/(?=(\b[A-Z]+)|(\b[a-z])|([A-Z][a-z]))/).flatten.compact.each do |e| @h << e[0].upcase end
    @h
  end

end
