class Acronym

  def self.abbreviate(str)
    @h = ""
    @ary2 = Array.new
    ary = str.gsub(/[\:\-\_]/," ").split(" ")

    ary.each do
       |w|   @ary2  << (w.split /(?=[A-Z][a-z])/)
     end
    @ary2.flatten.each do |e| @h << e[0] end

    # @ary2.each do |e| @h << e[0].capitalize end
      @h.upcase
  end

end
