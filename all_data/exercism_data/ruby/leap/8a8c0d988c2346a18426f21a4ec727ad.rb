=begin
Un año es bisiesto si es divisible por cuatro,
excepto los principios de año (los divisibles por 100),
que para ser bisiestos deben de ser divisibles también por 400.
=end

class Year
 
 def Year.leap?(anio)
 	return true if ((anio%4==0 ) && (anio%100!=0 || anio%400==0))
 end
end
