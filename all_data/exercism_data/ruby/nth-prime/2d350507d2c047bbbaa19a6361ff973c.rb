class Prime
 
 def self.nth(n)
 unless n>0 then raise ArgumentError,"input larger than 1 integer pls" end
 prime_list=[2]
  i=2
  until i>n
    prime_list<<=next_prime(prime_list)
    i+=1
    end
  prime_list.last
 end
 
 def self.next_prime(prime_list)
   _next=prime_list.last+1
   loop do
     return _next if prime?(_next,prime_list)
     _next+=1
   end
 end
 
 
 def self.prime?(n,prime_list)
  prime_list.each {|i| return false if n!=i and (n%i).zero?}
  return true if n< (prime_list.last **2)

   2.upto(n-1) do |i|
     return false if n.modulo(i).zero?
   end
   true
 end

end
