class Year
 def self.leap?(input)
   leap_year=false
   answer=""
   if (input % 4)==0 then leap_year=true end
   if (input % 100)==0 then leap_year=false end
   if (input % 400)==0 then  leap_year=true end

   leap_year
 end

end
