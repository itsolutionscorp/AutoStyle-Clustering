class Acronym

  def self.abbreviate(str)
    @h = ""
    @ary = Array.new
    @ary2 = Array.new
    str.scan(/\w+/).each do
      |e| @ary << e
      end
     @ary.each do |e| @ary2 << e.scan(/(?=([A-Z][a-z])|(^[a-z])|(^[A-Z][^a-z]))/) end
     @ary2.flatten.each do |e|
      unless e == nil
         @h << e[0]
         end
      end
      @h.upcase
  end

end
