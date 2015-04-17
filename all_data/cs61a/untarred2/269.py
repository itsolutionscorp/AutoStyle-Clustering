# CS 61A Summer 2014
# Name: Maria Can and Unzi Park
# Login: cs61a-lbb and cs61a-ldj

#we submitted the solutions posted becaue we read on Piazza that doing
#so is acceptable

############
# Nonlocal #
############

def make_accumulator():
    """Return an accumulator function that takes a single numeric argument and
    accumulates that argument into total, then returns total.

    >>> acc = make_accumulator()
    >>> acc(15)
    15
    >>> acc(10)
    25
    >>> acc2 = make_accumulator()
    >>> acc2(7)
    7
    >>> acc3 = acc2
    >>> acc3(6)
    13
    >>> acc2(5)
    18
    >>> acc(4)
    29
    """
    "*** YOUR CODE HERE ***"
    all_args = []
    def accumulate(n):
        all_args.append(n) # students will have trouble remembering they don't need nonlocal
        return sum(all_args) # students may do a for-loop which sums, that's also fine
    return accumulate # If students forget to actually return accumulate, they get scary looking NoneType errors

def make_accumulator_nonlocal():
    """Return an accumulator function that takes a single numeric argument and
    accumulates that argument into total, then returns total.

    >>> acc = make_accumulator_nonlocal()
    >>> acc(15)
    15
    >>> acc(10)
    25
    >>> acc2 = make_accumulator_nonlocal()
    >>> acc2(7)
    7
    >>> acc3 = acc2
    >>> acc3(6)
    13
    >>> acc2(5)
    18
    >>> acc(4)
    29
    """
    "*** YOUR CODE HERE ***"
    all_args_summed = 0 
    def accumulate(n):
        nonlocal all_args_summed # remembering this line
        all_args_summed += n # updating all_args_summed rather than returning all_args_summed + n
        return all_args_summed
    return accumulate # Again, NoneType errors imply this line was missing


#######
# OOP #
#######

class Amount(object):
    """An amount of nickels and pennies.

    >>> a = Amount(3, 7)
    >>> a.nickels
    3
    >>> a.pennies
    7
    >>> a.value
    22
    >>> a.nickels = 5
    >>> a.value
    32

    """
    "*** YOUR CODE HERE ***"
    def __init__(self, nickels, pennies): # reading doctests and realizing which order the __init__ arguments should be
        self.nickels = nickels
        self.pennies = pennies

    @property # remembering to use the decorator
    def value(self):
        return self.pennies + 5 * self.nickels

class MinimalAmount(Amount):
    """An amount of nickels and pennies that is initialized with no more than
    four pennies, by converting excess pennies to nickels.

    >>> a = MinimalAmount(3, 7)
    >>> a.nickels, a.pennies, a.value  # Creates a tuple
    (4, 2, 22)
    >>> a = MinimalAmount(7, 3)
    >>> a.nickels, a.pennies, a.value
    (7, 3, 38)
    >>> a = MinimalAmount(0, 50)
    >>> a.nickels, a.pennies, a.value
    (10, 0, 50)
    """
    "*** YOUR CODE HERE ***"
    def __init__(self, nickels, pennies): # explain that you're converting extra pennies to nickels
        Amount.__init__(self, nickels + (pennies // 5), pennies % 5) # could call Account.__init__ or set the variables separately

class VendingMachine:
    """A vending machine that vends some product for some price.

    >>> v = VendingMachine('candy', 10)
    >>> v.vend()
    'Machine is out of stock.'
    >>> v.restock(1)
    'Current candy stock: 1'
    >>> v.vend()
    'You must deposit $10 more.'
    >>> v.deposit(7)
    'Current balance: $7'
    >>> v.vend()
    'You must deposit $3 more.'
    >>> v.restock(1)
    'Current candy stock: 2'
    >>> v.deposit(5)
    'Current balance: $12'
    >>> v.vend()
    'Here is your candy and $2 change.'
    >>> v.deposit(10)
    'Current balance: $10'
    >>> v.vend()
    'Here is your candy.'
    >>> v.deposit(15)
    'Machine is out of stock. Here is your $15.'
    >>> v.vend()
    'Machine is out of stock.'
    >>> p = VendingMachine('pepsi', 21)
    >>> p.restock(100)
    'Current pepsi stock: 100'
    >>> p.deposit(100)
    'Current balance: $100'
    >>> p.vend()
    'Here is your pepsi and $79 change.'
    >>> p.deposit(21)
    'Current balance: $21'
    >>> p.vend()
    'Here is your pepsi.'
    """
    "*** YOUR CODE HERE ***"
    def __init__(self, product, price):
        self.product = product
        self.price = price
        self.stock = 0 # how many things it has to sell
        self.balance = 0 # how much money is inside machine

    def restock(self, num_products):
        self.stock += num_products
        return "Current {0} stock: {1}".format(self.product, self.stock)

    def deposit(self, money):
        if self.stock == 0:
            return "Machine is out of stock. Here is your ${0}.".format(money)  # should NOT update self.balance
        self.balance += money
        return "Current balance: ${0}".format(self.balance)

    def vend(self):
        if self.stock == 0:
            return "Machine is out of stock." # little things like missing periods
        elif self.balance < self.price:
            return "You must deposit ${0} more.".format(self.price - self.balance)
        self.stock -= 1 # remembering to update the stock
        change = self.balance - self.price
        self.balance = 0 # realize that balance is not the total amount of money in vending machine
        if change:
            return "Here is your {0} and ${1} change.".format(self.product, change)
        else:
            return "Here is your {0}.".format(self.product)




class MissManners:
    """A container class that only forward messages that say please.

    >>> v = VendingMachine('teaspoon', 10)
    >>> v.restock(2)
    'Current teaspoon stock: 2'
    >>> m = MissManners(v)
    >>> m.ask('vend')
    'You must learn to say please first.'
    >>> m.ask('please vend')
    'You must deposit $10 more.'
    >>> m.ask('please deposit', 20)
    'Current balance: $20'
    >>> m.ask('now will you vend?')
    'You must learn to say please first.'
    >>> m.ask('please hand over a teaspoon')
    'Thanks for asking, but I know not how to hand over a teaspoon'
    >>> m.ask('please vend')
    'Here is your teaspoon and $10 change.'
    """
    "*** YOUR CODE HERE ***"
    def __init__(self, obj):
        self.obj = obj

    def ask(self, method_call_attempt, *args):
        words = method_call_attempt.split() # one of many ways to see if the attempt starts with 'please '
        if words[0] != 'please':
            return "You must learn to say please first."
        actual_method_call = method_call_attempt[len('please '):] # remember the space!
        if not hasattr(self.obj, actual_method_call):
            return "Thanks for asking, but I know not how to {0}".format(actual_method_call)
        method = getattr(self.obj, actual_method_call)
        return method(*args) # this already returns a bound method (it's wrong to say self.obj.method(*args))



############
# Optional #
############

def make_withdraw(balance, password):
    """Return a password-protected withdraw function.

    >>> w = make_withdraw(100, 'hax0r')
    >>> w(25, 'hax0r')
    75
    >>> w(90, 'hax0r')
    'Insufficient funds'
    >>> w(25, 'hwat')
    'Incorrect password'
    >>> w(25, 'hax0r')
    50
    >>> w(75, 'a')
    'Incorrect password'
    >>> w(10, 'hax0r')
    40
    >>> w(20, 'n00b')
    'Incorrect password'
    >>> w(10, 'hax0r')
    "Your account is locked. Attempts: ['hwat', 'a', 'n00b']"
    >>> w(10, 'l33t')
    "Your account is locked. Attempts: ['hwat', 'a', 'n00b']"
    """
    "*** YOUR CODE HERE ***"
    incorrect_attempts = []
    def withdraw(amt, pwd):
        if len(incorrect_attempts) == 3:  # check for this first instead of if pwd != password
            return "Your account is locked. Attempts: {0}".format(incorrect_attempts)
        elif pwd != password:
            incorrect_attempts.append(pwd) # append before returning
            return "Incorrect password"
        nonlocal balance
        if amt > balance:
            return "Insufficient funds"
        balance -= amt # decrement balance instead of returning balance - amt
        return balance
    return withdraw 

# Old version
def count_change(a, coins=(50, 25, 10, 5, 1)):
    if a == 0:
        return 1
    elif a < 0 or len(coins) == 0:
        return 0
    return count_change(a, coins[1:]) + count_change(a - coins[0], coins)

# Version 2.0
def make_count_change():
    """Return a function to efficiently count the number of ways to make
    change.

    >>> cc = make_count_change()
    >>> cc(500, (50, 25, 10, 5, 1))
    59576
    """
    "*** YOUR CODE HERE ***"
    seen_before = {}
    def count_change(a, coins=(50, 25, 10, 5, 1)):
        if (a, coins) in seen_before:
            return seen_before[(a, coins)]
        if a == 0:
            ans =  1
        elif a < 0 or len(coins) == 0:
            ans = 0
        else:
            ans = count_change(a, coins[1:]) + count_change(a - coins[0], coins) 
        seen_before[(a, coins)] = ans
        return ans
    return count_change

