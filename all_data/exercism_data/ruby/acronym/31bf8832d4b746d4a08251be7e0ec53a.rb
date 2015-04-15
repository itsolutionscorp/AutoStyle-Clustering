class Acronym
   def self.abbreviate(arg)
      return /.+?(?=\:)/.match(arg)[0] if arg.include? ":"
      arg = arg.gsub(/\W/, ' ').split
      arg.each { |a| a[0] = a[0].upcase }
      arg = arg.join
      return arg.scan(/[A-Z]/).join
   end
end
