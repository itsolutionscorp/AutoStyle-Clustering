class Prime
    def self.nth(n)
        if(n<1)
            raise ArgumentError
        else
            prime_counter=0
            x=2
            while(prime_counter<n)
                y=2
                prime=true
                while(y<x && prime==true)
                    if (x%y==0)
                        prime=false
                    end
                    y+=1
                end
                if (prime==true)
                    prime_counter+=1
                    last_prime=x
                end
                x+=1
            end
            return last_prime
        end
    end
end
